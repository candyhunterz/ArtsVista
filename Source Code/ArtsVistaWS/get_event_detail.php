<?php
    require 'db_connect.php';
    if(isset($_GET['id'])){
        $id = trim($_GET['id']);
        
        if($people = $db->prepare("SELECT * FROM events WHERE id = ?")){
            $people->bind_param('i', $id);
            $people->execute();
            
            $people->bind_result($id, $title, $price, $desc, $created_at, $updated_at);
            
            $product = array();
                    
            while($people->fetch()){
                    $product["id"] = $id;
                    $product["title"] = $title;
                    $product["price"] = $price;
                    $product["description"] = $desc;
                    $product["created_at"] = $created_at;
                    $product["updated_at"] = $updated_at;
            }
            
            $response["success"] = 1;
            
            $response["product"] = array();
            $response["product"] = $product;
            
            
            echo json_encode($response);
        }
        else {
            // failed to insert row
            $response["success"] = 0;
            $response["message"] = "Oops! An error occurred.";
            
            // echoing JSON response
            echo json_encode($response);
        }
    } else {
        // required field is missing
        $response["success"] = 0;
        $response["message"] = "Required field(s) is missing";
        // echoing JSON response
        echo json_encode($response);
    }
    ?>