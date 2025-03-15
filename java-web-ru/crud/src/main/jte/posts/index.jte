@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        @for(var p : page.getPosts())
            <div>
                <h2><a href="${NamedRoutes.postPath(p.getId())}">${p.getName()}</a></h2>
            </div>
        @endfor
        <a href="${NamedRoutes.postsPath() + "?page=" + (page.getPage() + 1)}">Вперед</a>
        <a href="${NamedRoutes.postsPath() + "?page=" + (page.getPage() -1)}">Назад</a>
    </div>
`
)
