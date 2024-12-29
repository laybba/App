<?php
require 'connection.php';

// Check if data is sent via POST
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Retrieve the form data
    $email = $_POST['email'];
    $password = md5($_POST['password']);  // Password hashing, use bcrypt in production

    // Check if the email and password match a user in the database
    $checkUser = "SELECT * FROM user WHERE email='$email' AND password='$password'";
    $checkQuery = mysqli_query($con, $checkUser);

    if (mysqli_num_rows($checkQuery) > 0) {
        $response['error'] = '0000';
        $response['message'] = "Login successful";
    } else {
        
        $response['error'] = '0001';
        $response['message'] = "Invalid email or password";
    }

    // Return the response as JSON
    echo json_encode($response);
} else {
    
    echo json_encode(['error' => '0003', 'message' => 'Invalid request method']);
}
?>
