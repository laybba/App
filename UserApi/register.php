<?php
require 'connection.php';

$response = array();  // Initialize the response array

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Debugging: Print all POST data to check if it's being received
    error_log(print_r($_POST, true));  // This will log the POST data to the PHP error log

    // Check if the necessary data is set
    if (isset($_POST['username']) && isset($_POST['email']) && isset($_POST['password'])) {
        $username = $_POST['username'];
        $email = $_POST['email'];
        $password = md5($_POST['password']);  // Using md5 for password encryption (consider more secure options like bcrypt)

        // Check if the user already exists
        $checkUser = "SELECT * FROM user WHERE email='$email'";
        $checkQuery = mysqli_query($con, $checkUser);

        if (mysqli_num_rows($checkQuery) > 0) {
            // User already exists
            $response['error'] = '0002';
            $response['message'] = "User already exists";
        } else {
            // Insert the new user into the database
            $insertQuery = "INSERT INTO user(username, email, password) VALUES('$username', '$email', '$password')";
            $result = mysqli_query($con, $insertQuery);

            if ($result) {
                // Registration successful
                $response['response'] = '0000';
                $response['message'] = "Registration successful";
            } else {
                // Registration failed
                $response['error'] = '0001';
                $response['message'] = "Registration failed. Please try again.";
            }
        }
    } else {
        // Missing data
        $response['error'] = '0004';
        $response['message'] = 'Required fields are missing';
    }

    echo json_encode($response);
} else {
    // Invalid request method
    echo json_encode(['error' => '0003', 'message' => 'Invalid request method']);
}
?>
