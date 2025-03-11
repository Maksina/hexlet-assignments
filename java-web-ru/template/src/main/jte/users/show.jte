@import exercise.dto.users.UserPage;
@param UserPage page

<html>
<head>
    <meta charset="utf-8" />
    <title>Пользователи</title>
</head>
<body>
<h1>Пользователь с ID = ${page.getUser().getId()}</h1>
<div>
    <h2>Имя: ${page.getUser().getFirstName()}</h2>
    <h2>Фамилия: ${page.getUser().getLastName()}</h2>
    <p>E-mail: ${page.getUser().getEmail()}</p>
</div>

</body>
</html>