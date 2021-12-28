<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){

    if(
        
        isset($_POST['username']) and
        isset($_POST['score']) 
          ){
    

            $db = new DbOperations(); 

        $result =   $db->score(
              
                $_POST['username'],
                $_POST['score']
               
        );

        if($result==1)
		{
			$response['error'] = false; 
			$response['message'] = "Score updated successfully";
		}else if($result ==2) {
			$response['error'] = true; 
			$response['message'] = "Some error occurred please try again";			
		}else{
            $response['error'] = true; 
			$response['message'] = "Try again later";
        }

     }


}else {
        $response['error'] = true; 
		$response['message'] = "Invalid Request";
}



echo json_encode($response);

?>