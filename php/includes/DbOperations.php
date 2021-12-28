<?php 

	class DbOperations{

		private $con; 

		function __construct(){

			require_once dirname(__FILE__).'/DbConnect.php';

			$db = new DbConnect();

			$this->con = $db->connect();

		}

		public function User($name , $email , $username, $password,$phone){
			if($this->isUserExist($username)){
				return 0; 
			}else{
				$stmt = $this->con->prepare("INSERT INTO `username` (`name`, `phone`, `username`, `email`, `password`, `id`) VALUES ( ?, ?, ?, ?, ?, NULL);");
				$stmt->bind_param("sssss",$name,$email,$username,$password,$phone);

				if($stmt->execute()){
					return 1; 
				}
				else{
					return 2; 
				}
			}
		}	

		private function isUserExist($username){
			$stmt = $this->con->prepare("SELECT id FROM username WHERE username = ? ");
			$stmt->bind_param("s", $username);
			$stmt->execute(); 
			$stmt->store_result(); 
			return $stmt->num_rows > 0; 
		}
				
		public function usersLogin($username, $password){
			$query = "SELECT id FROM username WHERE username = '$username' AND password = '$password'";
			$result = mysqli_query($this->con,$query);
			if(mysqli_num_rows($result)>0){
				return 1; 

			}else{
				return 2;
			}
		}
		
		public function result(){
				$query = "SELECT * FROM `score` ORDER BY score DESC ";
				$resultArr = array();
			$result = mysqli_query($this->con, $query);
			if(mysqli_num_rows($result)>0){
				while($row = mysqli_fetch_assoc($result)){
					array_push($resultArr,$row);
					}
				echo json_encode($resultArr);	
			}else{
				echo "0 results";
			}
		}
		
		public function getUsersByUsername($username){
			$stmt = $this->con->prepare("SELECT * FROM username WHERE username = ?");
			$stmt->bind_param("s",$username);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}

		public function score($username, $score){

			$stmt = $this->con->prepare("INSERT INTO `score` (`id`, `username`, `score`) VALUES (NULL, ?, ?);");
			$stmt->bind_param("ss",$username,$score);
			
			if($stmt->execute()){
				return 1;
			}else{
				return 2;
			}
		}

	}
	?>