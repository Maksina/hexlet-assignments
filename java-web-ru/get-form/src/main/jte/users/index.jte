@import exercise.dto.users.UsersPage;
@param UsersPage page

<html>
<head>
    <meta charset="utf-8" />
    <title>Пользователи</title>
</head>
<body>
<form action="/users" method="get">
    <input type="search" name="term" value="${page.getTerm()}" />
    <input type="submit" value="Search" />
</form>
<h1>Список пользователей</h1>
@if(page.getUsers().isEmpty())
    <p>Пользователи отсутствуют</p>
@else
    @for(var user : page.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
            <p>${user.getEmail()}</p>
        </div>
    @endfor
@endif
</body>
</html>