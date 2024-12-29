<?php
require 'connection.php';

header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type');

$response = array(); // Initialize as an object

$email = isset($_POST['email']) ? $_POST['email'] : '';

if (empty($email)) {
    $response['status'] = 'error';
    $response['message'] = 'Email is required';
    echo json_encode($response);
    exit;
}

$query = "SELECT * FROM user WHERE email = '$email'";
$result = mysqli_query($con, $query);

if (!$result) {
    $response['status'] = 'error';
    $response['message'] = 'SQL Error: ' . mysqli_error($con);
    echo json_encode($response);
    exit;
}

if (mysqli_num_rows($result) > 0) {
    $user = mysqli_fetch_assoc($result);
    $response['status'] = 'success';
    $response['data'] = $user; // Now 'data' is an object
} else {
    // IMPORTANT: Return an empty object for 'data'
    $response['status'] = 'error';
    $response['message'] = 'User not found';
    $response['data'] = new stdClass(); // Empty object
}

echo json_encode($response);
mysqli_close($con);
?>
