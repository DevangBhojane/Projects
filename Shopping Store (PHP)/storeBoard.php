<?php
session_start();

$response = [];

// Check POST request method
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['bits'])) { // Check if 'bits' is provided
        $bits = json_decode($_POST['bits'], true); // Decode board data

        // Validate board format
        if (is_array($bits) && count($bits) > 0) {
            $valid = true;
            foreach ($bits as $row) {
                if (!is_array($row) || count($row) == 0) { // Validate row structure
                    $valid = false;
                    break;
                }
                foreach ($row as $cell) {
                    if (!is_int($cell) || ($cell !== 0 && $cell !== 1)) { // Validate cell values
                        $valid = false;
                        break 2;
                    }
                }
            }

            if ($valid) {
                $_SESSION['board'] = $bits; // Store board in session
                $response['msg'] = "Board state stored successfully."; // Success message
            } else {
                $response['msg'] = "Invalid board data."; // Invalid data message
            }
        } else {
            $response['msg'] = "Invalid input format."; // Invalid format message
        }
    } else {
        $response['msg'] = "No board data provided."; // No data message
    }
} else {
    $response['msg'] = "Invalid request method."; // Invalid method message
}

// Return JSON response
echo json_encode($response);
?>
