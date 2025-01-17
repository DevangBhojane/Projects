<?php
session_start();

$response = [  
    "plant" => "",  
    "msg" => "",    
    "debug" => "" 
];

// Check if plant exists in session
if (isset($_SESSION['plant'])) {  // Check for plant
    $response['plant'] = $_SESSION['plant'];  // Set plant data
    $response['msg'] = "Plant retrieved successfully.";  // Success message
} else {
    $response['msg'] = "No plant found. Please plant a seed first.";  // No plant message
}

// Return response
header('Content-Type: application/json');  // Set content type
echo json_encode($response);  // Send response
?>
