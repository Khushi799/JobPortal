<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Job</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #e6f2ff, #cce6ff);
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            background: rgba(255, 255, 255, 0.9);
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0px 4px 12px rgba(0,0,0,0.1);
            width: 400px;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #0056b3;
        }

        label {
            display: block;
            font-weight: bold;
            margin: 10px 0 5px;
            text-align: left;
        }

        input[type="text"], 
        input[type="number"], 
        textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }

        textarea {
            resize: none;
            height: 80px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px 18px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 15px;
            transition: 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add Job</h2>
        <form action="addJob" method="post">
            <label>Title:</label>
            <input type="text" name="title" required/>

            <label>Description:</label>
            <textarea name="description" required></textarea>

            <label>Category:</label>
            <input type="text" name="category" required/>

            <label>Location:</label>
            <input type="text" name="location" required/>

            <label>Salary:</label>
            <input type="number" name="salary" required/>

            <input type="submit" value="Add Job"/>
        </form>
    </div>
</body>
</html>