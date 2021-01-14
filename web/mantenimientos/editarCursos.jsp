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
    <div class="card" style="width: 50rem;padding: 30px 0px 0px 30px;">
        <h4 class="display-8"><c:out value='${titulo}'/></h4>
        <form name=mod_opcion method=POST action="/WebAppSistema/controladorPrincipal">
            <input type=HIDDEN name=accion value="GRABAR_CURSO">
            <input type=hidden name=operacion value='<c:out value='${operacion}'/>'>
            <input type=hidden name=xcod value="<c:out value='${curso.codigo}'/>">
            <table class="table table-striped table-hover">
                <tbody>
                    <tr><th>Codigo: </th>
                        <td><c:out value='${curso.codigo}'/> </td> </tr>
                    
<tr> <th>nombre</th><td><input name=xnom value="<c:out value='${curso.nombre}'/>" /></td> </tr>
<tr> <th>costo</th><td><input name=xcos value="<c:out value='${curso.costo}'/>" /></td> </tr>
<tr> <th>fec_ini</th><td><input name=xini value="<c:out value='${curso.fec_ini}'/>" /></td> </tr>
<tr> <th>fec_fin</th><td><input name=xfin value="<c:out value='${curso.fec_fin}'/>" /></td> </tr>
<tr> <th>duracion</th><td><input name=xdur value="<c:out value='${curso.duracion}'/>" /></td> </tr>
<tr> <th>sesiones</th><td><input name=xses value="<c:out value='${curso.sesiones}'/>" /></td> </tr>
<tr> <th>capacidad</th><td><input name=xcap value="<c:out value='${curso.capacidad}'/>" /></td> </tr>
<tr> <th>inscritos</th><td><input name=xins value="<c:out value='${curso.inscritos}'/>" /></td> </tr>
<tr> <th>estado</th><td><input name=xest value="<c:out value='${curso.estado}'/>" /></td> </tr>
                   
                    
                    
                </tbody>
            </table>
                
                
            <input type=submit name=boton class="btn btn-primary" value="GRABAR">
            <input type=submit name=boton class="btn btn-dark" value="CANCELAR">
        </form>
    </div>
    </body>
</html>
