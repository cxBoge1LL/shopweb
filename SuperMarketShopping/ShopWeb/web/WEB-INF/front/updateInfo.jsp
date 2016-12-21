<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="${ctx}/js/jquery-3.1.1.min.js"></script>
    <style>
        div {
            width: 100%;
            height: 100%;
            text-align: center;
        }

        table {
            width: 100%;
            height: 100%;
            color: #009bdb;
            border-radius: 10px 10px 10px 10px;
            border-color:#009bdb ;
        }
        table input{
           color: #009bdb;
            border-radius: 5px 5px 5px 5px;
        }
    </style>
</head>
<body >
<form id="updateInfo" method="POST">
    <div>
        <table>
            <tr>
                <td>
                    商品编码
                </td>
                <td>
                    <input type="text" name="product_id" value="${goodInfoList[0].product_id}"/>
                </td>
                <td style="display: none">该选项不能为空</td>
            </tr>
            <tr>
                <td>
                    条形码
                </td>
                <td>
                    <input type="text" name="bar_code" value="${goodInfoList[0].bar_code}"/>
                </td>
                <td style="display: none">该选项不能为空</td>
            </tr>
            <tr>
                <td>
                    类 别
                </td>
                <td>
                    <input type="text" name="type" value="${goodInfoList[0].type}"/>
                </td>
                <td style="display: none">该选项不能为空</td>
            </tr>
            <tr>
                <td>
                    商品名称
                </td>
                <td>
                    <input type="text" name="product_name" value="${goodInfoList[0].product_name}"/>
                </td>
                <td style="display: none">该选项不能为空</td>
            </tr>
            <tr>
                <td>
                    零售价
                </td>
                <td>
                    <input type="text" name="retail_price" value="${goodInfoList[0].retail_price}"/>
                </td>
                <td style="display: none">该选项不能为空</td>
            </tr>
            <tr>
                <td>
                    商品规格
                </td>
                <td>
                    <input type="text" name="product_standard" value="${goodInfoList[0].product_standard}"/>
                </td>
                <td style="display: none">该选项不能为空</td>
            </tr>
            <tr>
                <td>
                    计量单位
                </td>
                <td>
                    <input type="text" name="unit" value="${goodInfoList[0].unit}"/>
                </td>
                <td style="display: none">该选项不能为空</td>
            </tr>
            <tr>
                <td>
                    进货价
                </td>
                <td>
                    <input type="text" name="purchase_price" value="${goodInfoList[0].purchase_price}"/>
                </td>
                <td style="display: none">该选项不能为空</td>
            </tr>
            <tr>
                <td>
                    保质日期
                </td>
                <td>
                    <input type="text" name="expiration_date" value="${goodInfoList[0].expiration_date}"/>
                </td>
                <td style="display: none">该选项不能为空</td>
            </tr>
            <tr>
                <td>
                    库存
                </td>
                <td>
                    <input type="text" name="repertory" value="${goodInfoList[0].repertory}"/>
                </td>
                <td style="display: none">该选项不能为空</td>
            </tr>
        </table>
    </div>
</form>
</body>

</html>