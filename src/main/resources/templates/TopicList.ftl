<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Web Crawl </title>
    <link rel="stylesheet"
          type="text/css"
          href="<@spring.url '/css/style.css'/>"/>
</head>
<body>
<h3>Topics of Vnexpress</h3>
<br><br>
<div>

    <table border="1">
        <tr>
            <th>article_id</th>
            <th>category_id</th>
            <th>article_type</th>
            <th>original_cate</th>
            <th>site_id</th>
            <th>title</th>
            <th>lead</th>
            <th>share_url</th>
            <th>publish_time</th>
            <th>privacy</th>
            <th>author_id</th>
            <th>score</th>
            <th>location_name</th>

        </tr>
        <#list topics as topic>
            <tr>
                <td>${topic.article_id}</td>
                <td>${topic.category_id}</td>
                <td>${topic.article_type}</td>
                <td>${topic.original_cate}</td>
                <td>${topic.site_id}</td>
                <td>${topic.title}</td>
                <td>${topic.lead}</td>
                <td>${topic.share_url}</td>
                <td>${topic.publish_time}</td>
                <td>${topic.privacy}</td>
                <td>${topic.author_id}</td>
                <td>${topic.score}</td>
                <#if topic.location_name??>
                    <td>${topic.location_name}</td>
                <#else>
                    <td></td>
                </#if>

            </tr>
        </#list>
    </table>
</div>
</body>
</html>