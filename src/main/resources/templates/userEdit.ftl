<#import "parts/common.ftl" as c>

<@c.page>
User Editor
    <form action="/user" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="hidden" value="${user.id}" name="userId"/>
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="text" value="${user.username}" name="username"/>

        <button type="submit">Save</button>
    </form>

</@c.page>