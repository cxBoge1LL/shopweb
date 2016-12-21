<%@ page import="com.shopweb.bean.Cart" %>
<%@ page import="com.shopweb.bean.GoodInfo" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/6
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%
    Cart cart = null;
    if (request.getSession().getAttribute("cart") != null) {
        cart = (Cart) request.getSession().getAttribute("cart");
    }
    int goodSize = 0;
    HashMap<GoodInfo, Integer> goods = null;
    if (cart != null) {
        goods = cart.getGoods();
        goodSize = goods.size();
    }
%>
<head>
    <meta charset="utf-8"/>
    <title>超市管理系统</title>
    <link rel="stylesheet" href="${ctx}/css/shop.css"/>
    <link rel="stylesheet" href="${ctx}/css/layer.css"/>
    <!--<link rel="stylesheet" href="css/reset.css" />-->
    <link rel="stylesheet" href="${ctx}/css/style.css"/>
    <link rel="stylesheet" href="${ctx}/js/layer/skin/espresso/style.css">
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/js/shopcar/shop.js"></script>
    <script type="text/javascript" src="${ctx}/js/shopcar/modernizr.js"></script>
    <script type="text/javascript" src="${ctx}/js/shopcar/main.js"></script>
</head>
<body>
<div class="outer">
    <form id="addGood">
        <div class="divleft">
            <table>
                <tr>
                    <td>商品编码</td>
                    <td><input name="product_id" type="text"/></td>
                    <td style="display: none;vertical-align: middle;">*&nbsp;该选项不能为空</td>
                </tr>
                <tr>
                    <td>条形码</td>
                    <td><input name="bar_code" type="text"/></td>
                    <td style="display: none">*&nbsp;该选项不能为空</td>
                </tr>
                <tr>
                    <td>类 别</td>
                    <td>
                        <select name="type" class="selected_2">
                            <option value="袋">食品</option>
                            <option value="罐">百货</option>
                            <option value="桶">日化</option>
                            <option value="桶">纸卫</option>
                            <option value="桶">散称</option>
                            <option value="桶">烟酒</option>
                            <option value="桶">文体</option>
                            <option value="桶">节日用品</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>商品名称</td>
                    <td><input name="product_name" type="text"/></td>
                    <td style="display: none">*&nbsp;该选项不能为空</td>
                </tr>
                <tr>
                    <td>零售价</td>
                    <td><input name="retail_price" type="text"/></td>
                    <td style="display: none">*&nbsp;该选项不能为空</td>
                    <!--
                    <td><input type="text" value="元(￥)"
                        onfocus=" this.value='' "
                        onblur="if(this.value==''){this.value='元(￥)' }"  /></td>-->
                </tr>
                <tr>
                    <td>商品规格</td>
                    <td><input class="input_1" name="product_standard_1" type="text"/>
                        <select name="product_standard_2" class="selected_1">
                            <option value="g">g</option>
                            <option value="kg">kg</option>
                            <option value="双">双</option>
                            <option value="条">条</option>
                            <option value="只">只</option>
                        </select>
                    <td style="display: none">*&nbsp;该选项不能为空</td>
                    </td>
                </tr>
                <tr>
                    <td>计量单位</td>
                    <td>
                        <select name="unit" class="selected_2">
                            <option value="袋">袋</option>
                            <option value="罐">罐</option>
                            <option value="桶">桶</option>
                            <option value="桶">包</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>进货价</td>
                    <td><input name="purchase_price" type="text"/></td>
                    <td style="display: none">*&nbsp;该选项不能为空</td>
                    <!--<td><input type="text" value="元(￥)" onfocus=" this.value='' " onblur="if(this.value==''){this.value='元(￥)'}" /> </td>-->
                </tr>
                <tr>
                    <td>保质日期</td>
                    <td><input name="expiration_date" class="Wdate"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/></td>
                    <td style="display: none">*&nbsp;该选项不能为空</td>
                </tr>
                <tr>
                    <td>库存</td>
                    <td><input name="repertory" type="text"/></td>
                    <td style="display: none">*&nbsp;该选项不能为空</td>
                    <!--<td><input type="text" value="元(￥)" onfocus=" this.value='' " onblur="if(this.value==''){this.value='元(￥)'}" /> </td>-->
                </tr>
            </table>
        </div>
    </form>
    <div class="divtop">
        <table>
            <thead>
            <tr>
                <th width="3%"></th>
                <th width="14%">商品编码</th>
                <th width="15%">条形码</th>
                <th width="7%">类别</th>
                <th width="16%">商品名称</th>
                <th width="8%">零售价</th>
                <th width="10%">规格</th>
                <%--<th width="8%"></th>--%>
                <th width="10%">库存</th>
                <th width="15%">
                    查找: <label>
                    <input class="find" type="text" name="selectName"
                           onkeypress="if (event.keyCode == 13)doSelect('${ctx}');"/>
                </label>
                </th>
                <th width="2%">
                </th>
            </tr>
            </thead>
        </table>
    </div>
    <form>
        <div class="divright">
            <table>
                <tbody id="showtbody">
                <c:forEach items="${goodLists}" var="goodList" varStatus="status">
                    <%--<tr<c:if test="${status.index % 2!=0}">style="background-color: #7dcf85;"</c:if>>--%>
                    <tr <c:if test="${status.index%2!=0}">style="background-color: #7dcf85"</c:if>>
                        <td width="3%"><input type="checkbox" name="id" value="${goodList.product_id}"/></td>
                        <td width="14%">${goodList.product_id}</td>
                        <td width="15%">${goodList.bar_code}</td>
                        <td width="7%">${goodList.type}</td>
                        <td width="16%">${goodList.product_name}</td>
                        <td width="8%">${goodList.retail_price}</td>
                        <td width="10%">${goodList.product_standard}/${goodList.unit}</td>
                            <%--<td width="8%"></td>--%>
                        <td width="10%">&nbsp;${goodList.repertory}</td>
                        <td width="10%">
                            <a href="javascript:seeDetail('${ctx}','${goodList.product_id}');">详情</a>
                            <a href="javascript:updateInfo('${ctx}','${goodList.product_id}');">修改</a>
                        </td>
                        <td width="5%" id="img-${goodList.product_id}" class="car"
                            onclick="addCar('${ctx}','${goodList.product_id}')">
                            <img src="${ctx}/img/shopcar.png" width="40px" height="40px">
                        </td>
                        <td width="1%"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </form>
    <div class="divbottom">
        <ul>
            <%--<li class="pay">--%>
            <%--结算--%>
            <%--</li>--%>
            <li id="cd-cart-trigger" class="shopcar" style="position: relative">
                购物车
                <%
                    if (goodSize <= 0) {
                %>
                <sup
                        style="position:absolute;visibility: hidden; width:30px; height:30px;right: 0px;background: darkred url('${ctx}/img/shopcarciclre.png') no-repeat scroll  center;background-size:25px 25px;color: snow;font-weight: 700;line-height: 32px">0
                </sup>
                <%
                } else {
                %>
                <sup
                        style="position:absolute;visibility: visible; width:30px; height:30px;right: 0px;background: darkred url('${ctx}/img/shopcarciclre.png') no-repeat scroll  center;background-size:25px 25px;color: snow;font-weight: 700;line-height: 32px"><%=goodSize%>
                </sup>
                <%
                    }
                %>
            </li>
            <li class="delete" onclick="deleteMore('${ctx}')">
                删除商品
            </li>
            <li class="add">
                添加商品
            </li>
            <li class="sub" onclick="addGood('${ctx}')">
                提交
            </li>
        </ul>
    </div>
</div>
<div id="cd-shadow-layer"></div>

<div id="cd-cart">
    <h2>购物车</h2>
    <ul class="cd-cart-items">
        <%
            Set<GoodInfo> key = null;
            if (goods != null) {
                key = goods.keySet();
                Iterator i = key.iterator();
                while (i.hasNext()) {
                    GoodInfo goodInfo = (GoodInfo) i.next();
        %>
        <li>
            <h3><%=goodInfo.getProduct_name()%>
            </h3>
            数量:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="cart-<%=goodInfo.getProduct_id()%>"
                                                   class="cd-qty"><%=goods.get(goodInfo)%></span><br>
            单价:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="cd-price"><%=goodInfo.getRetail_price()%></span>
            <a href="javascript:removeFromCart('${ctx}','<%=goodInfo.getProduct_id() %>')" class="cd-item-remove"></a>
            <P>
                <img src="${ctx}/img/add.png"
                     onclick="addG('${ctx}','<%=goodInfo.getProduct_id() %>','<%=goodInfo.getRepertory()%>')">
                <img src="${ctx}/img/reduce.png"
                     onclick="reduceG('${ctx}','<%=goodInfo.getProduct_id() %>')">
            </P>
        </li>
        <%
                }
            }
        %>

        <%--<li>--%>
        <%--<span class="cd-qty">1x</span> Product Name--%>
        <%--<div class="cd-price">$9.99</div>--%>
        <%--<a href="#0" class="cd-item-remove cd-img-replace">Remove</a>--%>
        <%--</li>--%>
    </ul>

    <!-- cd-cart-items -->

    <div class="cd-cart-total">
        <%
            if (cart != null) {
        %>
        <p>总共 <span><%=cart.getTotolPrice()%></span></p>
        <%
        } else {
        %>
        <p>总共 <span>0.0</span></p>
        <%
            }
        %>

    </div>
    <!-- cd-cart-total -->

    <a href="javascript:checkOut('${ctx}')" class="checkout-btn">结算</a>

    <%--<p class="cd-go-to-cart">--%>
    <%--<a href="#0">去支付</a>--%>
    <%--</p>--%>
</div>
<!-- cd-cart -->
</body>

</html>