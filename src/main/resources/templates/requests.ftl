<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="/js/script.js"></script>
    <link rel="stylesheet" href="/css/mainStyle.css">
    <title>Requests</title>
</head>
<body>
<div class="form">
    <div class="form-panel one">
        <div class="form-header">
            <h1>New request</h1>
        </div>
        <div class="form-content">
            <form action="/addRequest" method="post">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" required="required"/>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <input type="text" id="description" name="description" required="required"/>
                </div>
                <div class="form-group">
                    <button type="submit">Add</button>
                </div>
            </form>
            <table class="datatable">
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th></th>
                    <th></th>
                </tr>
                <#list requests as request>
                    <tr>
                        <td>${request.id}</td>
                        <td>${request.title}</td>
                        <td>${request.description}</td>
                        <td><button type="submit">Edit</button></td>
                        <td><button type="submit">Delete</button></td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
</div>
</body>
</html>
