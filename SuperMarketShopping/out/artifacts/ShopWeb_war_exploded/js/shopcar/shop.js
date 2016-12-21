//全局变量
var a = true;//控制添加商品文本变换
var carI = 0;//购物车上标数字
$(function () {

    //1.隐藏

    $(".add").on("click", function () {
        $(".divleft").toggleClass("divleft2");
        $(".divright,.divtop").toggleClass("divrightwidth");
        $(".divbottom").toggleClass("divrightwidth");
        $(".sub").fadeToggle(1);
        $(".find").toggle();
        if (a) {
            $(this).html("返回");
            a = false;
        } else {
            $("#addGood").find("input[type$='text']").parent().next().css("display", "none")
            $(this).html("添加商品");
            a = true;
        }

    });

    // $(".sub").on('click', function () {
    //     layer.msg("提交成功");
    //     $(".divleft").toggleClass("divleft2");
    //     $(".divright,.divtop").toggleClass("divrightwidth");
    //     $(".divbottom").toggleClass("divrightwidth");
    //     $(".find").toggle();
    //     $(".sub").fadeToggle(1);
    //     if (a) {
    //         $(".add").html("返回");
    //         a = false;
    //     } else {
    //         $(".add").html("添加商品");
    //         a = true;
    //     }
    // });

    //
    // // 2.结算
    // $(".pay").on('click', function () {
    //     layer.open({
    //         title: '结算',
    //         type: 2,
    //         content: 'layer.html',
    //         //			content: '<h3>你需要缴纳的金额为</h3><hr><h1>1000大洋</h1>',
    //         area: ['420px', '240px'], //宽高
    //         shadeClose: true,
    //     });
    // });
    shopcarTop();
});
function shopcarTop() {

    //购物车上标显示事件
    $(".car").click(function () {
        $(this).css("visibility", "hidden");
        carI++;
        $(".shopcar").children().css("visibility", "visible");
        $(".shopcar").children().html(carI);
    });
}
//模糊查询操作
function doSelect(ctx) {
    $.ajax(
        {
            type: "POST",
            url: ctx + "/front/selectgood.action",
            data: {selectName: $('input[name="selectName"]').val()},
            dataType: "json",
            success: function (data) {
                if (data.length == 0) {
                    layer.msg("未查到对应的商品,请重新输入");
                } else {
                    $("#showtbody").empty();//清空列表 接受到返回的数据拼接到页面上
                    for (var i = 0; i < data.length; i++) {
                        if (i % 2 != 0) {
                            var html = "<tr style=\"background-color: #7dcf85\">";
                        } else {
                            var html = "<tr>";
                        }
                        html = html + "<td width=\"3%\"><input type=\"checkbox\" name=\"id\" value=\"" + data[i].product_id + "\"/></td>" +
                            "<td width=\"14%\">" + data[i].product_id + "</td>" +
                            "<td width=\"15%\">" + data[i].bar_code + "</td>" +
                            "<td width=\"7%\">" + data[i].type + "</td>" +
                            "<td width=\"16%\">" + data[i].product_name + "</td> " +
                            "<td width=\"8%\">" + data[i].retail_price + "</td>" +
                            "<td width=\"10%\">" + data[i].product_standard + "/" + data[i].unit + "</td>" +
                            "<td width=\"10%\">" + data[i].repertory + "</td> " +
                            "<td width=\"10%\"><a href=\'#\' id=\'detail" + data[i].product_id + "\'>详情</a> " +
                            "<a href=\'#\' id=\'update" + data[i].product_id + "\'>修改</a></td>" +
                            "<td width=\"5%\" id=\"img-" + data[i].product_id + "\" class=\"car\" onclick=\"addCar('" + ctx + "','" + data[i].product_id + "')\"><img src=\"" + ctx + "/img/shopcar.png\" width=\"40px\" height=\"40px\"></td>" +
                            "<td width=\"1%\"></td></tr> ";
                        $("#showtbody").append(html);
                        $("#detail" + data[i].product_id + "").attr("href", "javascript:seeDetail('" + ctx + "','" + data[i].product_id + "');");
                        $("#update" + data[i].product_id + "").attr("href", "javascript:updateInfo('" + ctx + "','" + data[i].product_id + "');");
                    }
                    shopcarTop();
                }
            },
            error: function (data) {
                console.log(data);
                alert("查询失败")
            }
        }
    )
}

//添加商品
function addGood(ctx) {
    var isN = isNUll($("#addGood").find("input[type$='text']"));
    if (!isN) {
        layer.msg("数据不能为空");
    } else {
        $.ajax({
            type: "POST",
            url: ctx + "/front/addGood.action",
            data: $("#addGood").serialize(),
            dataType: "json",
            success: function (data) {
                if (data == true) {
                    layer.msg("商品保存成功");
                    $("#addGood").find("input[type$='text']").val("");
                    window.location.reload();
                }
                if (data == false) {
                    layer.msg("商品已存在或者存储异常");
                }
            },
            error: function (data) {
                layer.msg("数据存储异常");
                layer.msg(data);
            }
        })
    }

}

function isNUll(str) {
    var num = 0;
    str.each(function () {
        if ($(this).val() == "") {
            num++;
            $(this).parent().next().css("display", "").css("color", "#DE4A12");
        } else {
            $(this).parent().next().css("display", "none")
        }
    });
    if (num > 0) {
        return false;
    } else {
        return true;
    }
}

//删除操作
function deleteMore(ctx) {
    var isChecked = $("input[name='id']").is(':checked');
    if (isChecked == false) {
        layer.msg("请选中你需要删除的商品");
        return;
    }
    var ids = new Array();
    var num = 0;
    $('input[name="id"]:checked').each(function () {
        ids[num] = $(this).val();
        num++;
    });

    $.ajax({
        type: "GET",
        data: {ids: ids},
        url: ctx + "/front/deleteMore.action",
        dataType: "json",
        success: function (data) {
            if (data != null) {
                layer.msg("删除成功");
                $("#showtbody").empty();
                for (var i = 0; i < data.length; i++) {
                    if (i % 2 != 0) {
                        var html = "<tr style=\"background-color: #7dcf85\">";
                    } else {
                        var html = "<tr>";
                    }
                    html = html + "<td width=\"3%\"><input type=\"checkbox\" name=\"id\" value=\"" + data[i].product_id + "\"/></td>" +
                        "<td width=\"14%\">" + data[i].product_id + "</td>" +
                        "<td width=\"15%\">" + data[i].bar_code + "</td>" +
                        "<td width=\"8%\">" + data[i].type + "</td>" +
                        "<td width=\"16%\">" + data[i].product_name + "</td> " +
                        "<td width=\"8%\">" + data[i].retail_price + "</td>" +
                        "<td width=\"10%\">" + data[i].product_standard + "/" + data[i].unit + "</td>" +
                        "<td width=\"10%\">" + data[i].repertory + "</td> " +
                        "<td width=\"10%\"><a href=\"javascript:seeDetail('"+ctx+"','" + data[i].product_id + "');\">详情</a> " +
                        "<a href=\"javascript:updateInfo('"+ctx+"','"+ data[i].product_id+"');\">修改</a></td>" +
                        "<td width=\"5%\"><img src=\"" + ctx + "/img/shopcar.png\" width=\"40px\" height=\"40px\"></td>" +
                        "<td width=\"1%\"></td></tr> ";
                    $("#showtbody").append(html);
                }
                $('input:checkbox').removeAttr('checked');
            } else {
                layer.msg("删除失败");
            }
        },
        error: function () {
            layer.msg("操作数据异常,删除失败");

        }
    });
}

// 查看详情
function seeDetail(ctx, id) {
    $.ajax({
        type: "GET",
        url: ctx + "/front/seeDetail.action",
        data: {id: id},
        dataType: "json",
        success: function (data) {
            var str =
                "<span>商品编码:<span>" + data[0].product_id + "<br>" +
                "<span>条形码:&nbsp;<span>" + data[0].bar_code + "<br>" +
                "<span>商品类型:<span>" + data[0].type + "<br>" +
                "<span>零售价:<span>&nbsp;" + data[0].retail_price + "<br>" +
                "<span>商品规格:<span>" + data[0].product_standard + "\/" + data[0].unit + "<br>" +
                "<span>保质日期:<span>" + data[0].expiration_date + "<br>" +
                "<span>库存:<span>&nbsp;&nbsp;" + data[0].repertory;

            layer.open({
                title: "<span>你选择了:</span>" + "<h2 style='color:darkred;'>" + data[0].product_name + "</h2>",
                type: 1,
                content: "<h4 style='text-align: center'>" + str + "</h4>",
                area: ['420px', '240px'], //宽高
                shadeClose: true,
            });
        },
        error: function () {
            layer.msg("查看失败")
        }
    })
}

//更新数据操作
function updateInfo(ctx, id) {
    $.ajax({
        type: "POST",
        url: ctx + "/front/seeDetail.action",
        data: {id: id},
        dataType: "json",
        success: function (data) {
            layer.open({
                    title: "<h3>" + data[0].product_name + "</h3>",
                    type: 2,
                    skin: 'layer-ext-espresso',
                    content: ctx + "/front/updateInfo.action?id=" + data[0].product_id,
                    area: ['420px', '410px'], //宽高
                    btn: ['保存', '返回'],
                    yes: function (index) {
                        var form = layer.getChildFrame('form');
                        if (!isNUll(form.find("input[type$='text']"))) {
                            layer.msg("数据不能为空");
                        } else {
                            $.ajax({
                                type: "POST",
                                url: ctx + "/front/updateGood.action",
                                data: form.serialize(),
                                dataType: "json",
                                success: function (data) {
                                    if (data == true) {
                                        layer.msg("更新成功", {time: 1000});
                                    }
                                    if (data == false) {
                                        layer.msg("更新失败");
                                    }
                                },
                                error: function () {
                                    layer.msg("提交失败");
                                }
                            })
                            layer.close(index);
                        }
                    }
                }
            )
        },
        error: function () {
            layer.msg("数据异常");
        }
    })
}

//加入购物车
function addCar(ctx, id) {
    $.ajax({
            type: "POST",
            url: ctx + "/front/addCart.action",
            data: {id: id},
            dataType: "json",
            success: function (data) {
                loadGoods(ctx, data);
            },
            error: function () {
                layer.msg("失败");
            }
        }
    )
};


//从购物车移除商品
function removeFromCart(ctx, id) {
    $.ajax({
        type: 'POST',
        url: ctx + "/front/removeFromCart.action",
        data: {id: id},
        dataType: "json",
        success: function (data) {
            loadGoods(ctx, data);
            $("#img-" + id + "").css("visibility", "visible");

            //清除商品的时候需要更改商品上标
            carI--;
            $(".shopcar").children().html(carI);
            if (carI <= 0) {
                carI=0;
                $(".shopcar").children().css("visibility", "hidden");
            }
        },
        error: function () {
            layer("删除失败");
        }
    })
}


//刷新购物车数据操作

function loadGoods(ctx, data) {
    $(".cd-cart-items").empty();
    var totalPrice = 0.0;
    for (var key in data) {
        var goodinfo = eval("(" + key + ")");
        var html = "<li><h3>商品名:" + goodinfo.product_name + "</h3>" +
            "数量:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id=\"cart-" + goodinfo.product_id + "\" class=\"cd-qty\">" + data[key] + "</span><br>" +
            "单价:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"cd-price\">" + goodinfo.retail_price + "</span>" +
            "<a href=\"#\" id=\'remove" + goodinfo.product_id + "\' class=\"cd-item-remove cd-img-replace\">Remove</a>" +
            " <P> <img onclick=\"addG('" + ctx + "','" + goodinfo.product_id + "','"+goodinfo.repertory+"')\" src=\"" + ctx + "/img/add.png\">" +
            "<img onclick=\"reduceG('" + ctx + "','" + goodinfo.product_id + "')\" src=\"" + ctx + "/img/reduce.png\"> " +
            "</P></li>";
        totalPrice += goodinfo.retail_price * data[key];
        $(".cd-cart-items").append(html);
        $("#remove" + goodinfo.product_id + "").attr("href", "javascript:removeFromCart('" + ctx + "','" + goodinfo.product_id + "')");
    }
    if ($("#cd-cart").find(".cd-cart-total").length > 0) {
        $(".cd-cart-total").find("span").html(totalPrice);
    } else {
        var html = "<div class=\"cd-cart-total\"><p>总共 <span><%=cart.getTotolPrice()%></span></p></div> <a href=\"#\" class=\"checkout-btn\">结算</a> <p class=\"cd-go-to-cart\">"
        $("#cd-cart").append(html);
        $(".cd-cart-total").find("span").html(totalPrice);
        $(".checkout-btn").attr("href", "javascript:checkOut('" + ctx + "')");
    }
}

//购物车加减操作
//addG
function addG(ctx, id,repertory) {
    var number = Number($("#cart-" + id + "").html());
    number++;
    if(number>repertory){
        layer.msg("库存不足");
    }else {
        $("#cart-" + id + "").html(number);
        updateNumber(ctx, number, id);
    }
}
//reduceG
function reduceG(ctx, id) {
    var number = Number($("#cart-" + id + "").html());
    number--;
    if (number == 0) {
        removeFromCart(ctx, id);
    }
    $("#cart-" + id + "").html(number);
    updateNumber(ctx, number, id);
}

//异步修改购买商品数据
function updateNumber(ctx, number, id) {
    $.ajax({
        type: "POST",
        url: ctx + "/front/updateCart.action",
        data: {id: id, number: number},
        dataType: "json",
        success: function (data) {
            $(".cd-cart-total").find("span").html(data);
        },
        error: function () {
            layer("出现错误了");
        }
    })
}

//结算跳后台清空session
function checkOut(ctx) {
    $.ajax({
        type: "POST",
        url: ctx + "/front/checkOut.action",
        dataType: "json",
        success: function (data) {
            //清空购物车数据
            $(".cd-cart-items").empty();
            var price=$(".cd-cart-total").find("span").html();
            $(".cd-cart-total").find("span").html("0.00");
            var html1 = "<h3>您购买了:</h3><br>";
            var html2="";
            for (var key in data) {
                var goodinfo = eval("(" + key + ")");
                html2+= goodinfo.product_name + "*" + data[key] + "<br>";
            }
            html2+="<br>需要支付的金额为:<strong style='color: darkred'>"+price+"</strong>";
            layer.open({
                title: "结算清单",
                type: 1,
                content:html1+ "<center>"+html2+"</center>",
                area: ['400px', '300px'],
                cancel: function(){
                    window.location.reload();
                }
            });

        },
        error: function () {

        }
    })
}







