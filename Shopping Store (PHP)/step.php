<?php
// Start session
session_start();

$response = [];

// Count live neighbors
function countLiveNeighbors($board, $x, $y) {
    $neighbors = [ // Neighbor positions
        [-1, -1], [-1, 0], [-1, 1],
        [0, -1],          [0, 1],
        [1, -1], [1, 0], [1, 1]
    ];
    $liveCount = 0;
    $rows = count($board); // Get rows
    $cols = count($board[0]); // Get columns

    foreach ($neighbors as $neighbor) {
        $nx = $x + $neighbor[0]; // Neighbor X
        $ny = $y + $neighbor[1]; // Neighbor Y

        if ($nx >= 0 && $nx < $rows && $ny >= 0 && $ny < $cols) { // Valid neighbor
            $liveCount += $board[$nx][$ny]; // Count live neighbor
        }
    }

    return $liveCount; // Return count
}

// Check board existence
if (isset($_SESSION['board'])) {
    $currentBoard = $_SESSION['board']; // Get current board
    $rows = count($currentBoard); // Get number of rows
    $cols = count($currentBoard[0]); // Get number of columns
    $newBoard = []; // Initialize new board

    for ($i = 0; $i < $rows; $i++) {
        $newBoard[$i] = []; // Initialize row
        for ($j = 0; $j < $cols; $j++) {
            $liveNeighbors = countLiveNeighbors($currentBoard, $i, $j); // Get live neighbors

            if ($currentBoard[$i][$j] == 1) { // Cell is alive
                $newBoard[$i][$j] = ($liveNeighbors == 2 || $liveNeighbors == 3) ? 1 : 0; // Apply rules
            } else { // Cell is dead
                $newBoard[$i][$j] = ($liveNeighbors == 3) ? 1 : 0; // Apply rules
            }
        }
    }

    $_SESSION['board'] = $newBoard; // Update session board
    $response['bits'] = json_encode($newBoard); // Return new board
    $response['msg'] = "Conway step executed successfully."; // Success message
} else {
    $response['bits'] = json_encode([]); // No board found
    $response['msg'] = "No board state found. Initialize the board first."; // Error message
}

// Return response
echo json_encode($response);

?>

