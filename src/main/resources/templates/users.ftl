<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <script src="/js/script.js"></script>
    <link rel="stylesheet" href="/css/mainStyle.css">
    <title>Login Form - Modal</title>
</head>

<body>
<div class="form" >
    <div class="form-panel one">
        <div class="form-header">
            <h1>Sign up</h1>
        </div>
        <div class="form-content">
            <form action="/addUser" method="post">
                <div class="form-group">
                    <label for="firstname">First name</label>
                    <input type="text" id="firstname" name="firstname" required="required"/>
                </div>
                <div class="form-group">
                    <label for="lastname">Last name</label>
                    <input type="text" id="lastname" name="lastname" required="required"/>
                </div>
                <div class="form-group">
                    <label for="nickname">Nickname</label>
                    <input type="text" id="nickname" name="nickname" required="required"/>
                </div>
                <div class="form-group">
                    <label for="login">Login</label>
                    <input type="text" id="login" name="login" required="required"/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required="required"/>
                </div>
                <div class="form-group">
                    <button type="submit">Register</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div>

    <table class="datatable">
        <tr>
            <th>Id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Nickname</th>
            <th>Login</th>
        </tr>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.nickname}</td>
                <td>${user.login}</td>
            </tr>
        </#list>
    </table>

</div>
</body>
</html>
