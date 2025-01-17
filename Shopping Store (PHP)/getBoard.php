<?php

session_start();

$response = [];

// Check if board exists
if (isset($_SESSION['board'])) {
    $response['bits'] = json_encode($_SESSION['board']); // Retrieve board state
    $response['msg'] = "Board state retrieved successfully."; // Success message
} else {
    $response['bits'] = json_encode([]); // Initialize empty board
    $response['msg'] = "No board state found. Initializing empty board."; // No board found message
}

// Return JSON response
echo json_encode($response);

?>

