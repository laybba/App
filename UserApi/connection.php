<?php

  $hostName ='localhost';
  $userName ='root';
  $userPass ='';
  $dbName ='userdata';

  $con=mysqli_connect($hostName,$userName,$userPass,$dbName);



// if (!$con) {
   
//     $response['status'] = 'failed';
//     $response['message'] = 'Connection failed';
// } else {
   
//     $response['status'] = 'successful';
//     $response['message'] = 'Connection successful';
// }

//echo json_encode($response);
?>