 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" 
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">
    </head>
    <body>
    <div class="card" style="width: 50rem; padding: 30px 0px 0px 30px;">
        <h4 class="display-8">Seleccionar Alumno</h4>
        <form method=POST action="/WebAppSistema/controladorPrincipal">
            <input type=HIDDEN name=accion value="matriculaMostrarContratoCursos">
            <input type=HIDDEN name=xndoc value="<c:out value='${xndoc}'/>">
            <h4 class="display-8">Datos de Alumno</h4> 
            <h4 class="display-8">Seleccione los cursos</h4>
            <table class="table table-striped table-hover">
                <tr><td>
                Cursos disponibles:
                </td></tr>
                <tr><td>
                <select name="xcodCurso" size="10" class="form-control" id="exampleFormControlSelect2" multiple>
                    <c:forEach items="${arrCursos}" var="curso">
                    <option value=<c:out value='${curso.codigo}'/> >
                        <c:out value='${curso.nombre}'/>
                    </option>
                    </c:forEach>
                </select>
                </td></tr>
            <tr><td>
                <input type=submit name=boton class="btn btn-primary" value="GuardarCurso">
                
            </td></tr>
        </form>

    </div>
    </body>
</html>
