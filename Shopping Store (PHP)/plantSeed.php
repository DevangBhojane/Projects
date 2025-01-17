<?php
session_start();


$response = [
    "plant" => "",
    "msg" => "Plant initialized.",
    "debug" => ""
];

// Check for seed and steps input

if (isset($_POST['seed']) && isset($_POST['steps'])) {
    $_SESSION['plant'] = $_POST['seed'];
    $_SESSION['maxSteps'] = (int)$_POST['steps'];
    $_SESSION['currentSteps'] = 0; // Reset step count

    // Update response with seed data
    $response['plant'] = $_SESSION['plant'];
    $response['msg'] = "Seed planted with max steps set to {$_SESSION['maxSteps']}.";
} else {
    // Handle missing input error
    $response['msg'] = "Error: Missing seed or steps.";
}

// Output JSON response

header('Content-Type: application/json');
echo json_encode($response);
?>
