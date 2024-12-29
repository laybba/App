<?php
require 'connection.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $email = $_POST['email'];
    $new_password = $_POST['new_password']; // Assume the new password comes as plaintext for simplicity
    $hashed_password = md5($new_password); // Encrypt the password

    // Check if email exists in the database
    $checkUserQuery = "SELECT * FROM user WHERE email='$email'";
    $checkUserResult = mysqli_query($con, $checkUserQuery);

    if (mysqli_num_rows($checkUserResult) > 0) {
        // Update password
        $updatePasswordQuery = "UPDATE user SET password='$hashed_password' WHERE email='$email'";
        $updateResult = mysqli_query($con, $updatePasswordQuery);

        if ($updateResult) {
            $response = array(
                "error" => "0000",
                "message" => "Password updated successfully"
            );
        } else {
            $response = array(
                "error" => "0001",
                "message" => "Failed to update password"
            );
        }
    } else {
        $response = array(
            "error" => "0002",
            "message" => "Email not found"
        );
    }

    // Send JSON response
    echo json_encode($response);
} else {
    $response = array(
        "error" => "0003",
        "message" => "Invalid request method"
    );
    echo json_encode($response);
}
?>
