<?php
/*
 * Following code will list all the events
 */
// array for JSON response
$response = array();
// include db connect class

require 'db_connect.php';
if($result = $db->query("SELECT events.id, events.description, events.title, pictures.Imageable_id, pictures.id, events.vendor_id, 
                                pictures.Image, pictures.Image_type, events.ticket, events.video, vendors.name, vendors.address1, 
                                vendors.latitude, vendors.longitude, occurrences.start_date
    FROM `events` 
    JOIN `pictures` ON events.id=pictures.Imageable_id 
    JOIN `vendors` ON vendors.id = events.vendor_id
    JOIN `occurrences` ON occurrences.event_id = events.id
    WHERE pictures.Image_type = 'main_image' AND events.id != 7 AND events.id !=22 AND events.id != 23 
    LIMIT 26")){
    if($count = $result->num_rows){
        $response["events"] = array();
        
        while($row = $result->fetch_object()){
            $events = array();
            $events["id"] = $row->id;
            $events["title"] = $row->title;
            //$events["price"] = $row->price;
            //$events["description"] = $row->description;
            //$events["created_at"] = $row->created_at;
            //$events["updated_at"] = $row->updated_at;
            $events["ImageID"] = $row->id;
            $events["ImageEvent_ID"] = $row->Imageable_id;
            $events["Image"] = "https://production-artsvista.s3.amazonaws.com/uploads/picture/image/" . $row->id . "/" . "event_" . $row->Image . "?1447441421";
            $events["ticket"] = $row->ticket;
            $events["video"] = $row->video;
            $events["vendor_name"] = $row->name;
            $events["vendor_address"] = $row->address1;
            $events["vendor_id"] = $row->vendor_id;
            $events["latitude"] = $row->latitude;
            $events["longitude"] = $row->longitude;
            $events["description"] = $row->description;
            $events["start_date"] = $row->start_date;
            array_push($response["events"], $events);
        }
        
        $response["success"] = 1;
        
        // echoing JSON response
        
        echo json_encode($response);
         
        // echo $response["products"];
    }
        
        $result->free();
} else {
    // no events found
    $response["success"] = 0;
    $response["message"] = "No events found";
    // echo no users JSON
    echo json_encode($response);
}
?>