<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <#if Sesion?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
    </#if>
    <@l.login "/login" false/>
</@c.page>