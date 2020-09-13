  
<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){
	if(isset($_POST['username']) and isset($_POST['password'])){
		$db = new DbOperations(); 

		$result = $db->usersLogin($_POST['username'], $_POST['password']);
		if($result == 1){
			$user = $db->getUsersByUsername($_POST['username']);
			$response['error'] = false; 
			$response['id'] = $user['id'];
			$response['name'] = $user['name']; 
			$response['email'] = $user['email']; 
			$response['username'] = $user['username']; 

		}else{
			$response['error'] = true; 
			$response['message'] = "Invalid username or password!";			
		}

	}else{
		$response['error'] = true; 
		$response['message'] = "Required fields are missing";
	}
}else {
	$response['error'] = true; 
	$response['message'] = "Invalid Request";
	
}

echo json_encode($response);
?>