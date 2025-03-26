@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello!</title>
</head>
<body>
<main>
    @if(page.getName() != null)
        <h1>${page.getName().toString()}</h1>
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <input type="submit" value="Выйти">
        </form>
    @endif

    @if(page.getName() == null)
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @endif
</main>
</body>
</html>