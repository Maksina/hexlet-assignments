@import exercise.dto.users.UsersPage;
@param UsersPage page

@template.layout.page(
content = @`
    @for(var user : page.getUsers())
        <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
        <p>${user.getEmail()}</p>
    @endfor
`
)