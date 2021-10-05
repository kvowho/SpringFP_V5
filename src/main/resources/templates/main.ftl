<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>
    <@l.logout />
</div>
<div>
    <form method="post">
        <input type="text" name="comment" placeholder="Comment" />
        <input type="text" name="status" placeholder="Status">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Добавить</button>
    </form>
</div>
<div>Список сообщений</div>
<form method="get" action="/main">
    <input type="text" name="filter" value="${filter}">
    <button type="submit">Найти</button>
</form>
<#list invoices as invoice>
<div>
    <b>${invoice.id}</b>
    <span>${invoice.comment}</span>
    <i>${invoice.status}</i>
    <strong>${invoice.authorName}</strong>
</div>
<#else>
No message
</#list>
</@c.page>
