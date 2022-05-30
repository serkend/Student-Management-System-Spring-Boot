<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<body>
<h2>All employees</h2>

<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Department</th>
        <th>Salary</th>
        <th>Operations</th>
    </tr>

    <c:forEach var="person" items="${people}">
        <%--        Работаем с объектами под видом атрибутов--%>

<%--        <c:url var="updateButton" value="/updateInfo">--%>
<%--            <c:param name="empId" value="${person.id}"/>--%>
<%--        </c:url>--%>

<%--        <c:url var="deleteButton" value="/deleteEmployee">--%>
<%--            <c:param name="empId" value="${person.id}"/>--%>
<%--        </c:url>--%>

        <tr>
            <td>${person.firstName}</td>

            <td>
                <input type="button" value="Update"
                       onclick="window.location.href='${updateButton}'"/>
                <input type="button" value="Delete"
                       onclick="window.location.href='${deleteButton}'"/>
            </td>
        </tr>
    </c:forEach>

</table>
<br>
<input type="button" value="Add" onclick="window.location.href = 'addNewEmployee'"/>


</body>


</html>