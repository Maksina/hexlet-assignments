@import exercise.dto.users.UserPage;
@param UserPage page

@template.layout.page(
content = @`
    <h1>Пользователь с ID = ${page.getUser().getId()}</h1>
    <div>
        <h2>Имя: ${page.getUser().getFirstName()}</h2>
        <h2>Фамилия: ${page.getUser().getLastName()}</h2>
        <p>E-mail: ${page.getUser().getEmail()}</p>
    </div>
`
)