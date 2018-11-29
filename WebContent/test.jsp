<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="https://cdn.bootpay.co.kr/js/bootpay-2.0.12.min.js" type="application/javascript">
    BootPay.request({
    	price: '1000', 
    	application_id: "5bfe6266396fa67afed7c874",
    	name: '블링블링 마스카라', 
    	pg: 'lgup',
    	method: 'card', 
    	show_agree_window: 0,
    	
    	user_info: {
    		username: '이유성',
    		email: 'asergh45@naver.com',
    		addr: '석관동 261-157',
    		phone: '010-1234-4567'
    	},
    	order_id: '123',
    	account_expire_at: '2018-05-25'
    }).error(function (data) {
    	
    	console.log(data);
    }).cancel(function (data) {
    	
    	console.log(data);
    }).ready(function (data) {
    	
    	console.log(data);
    }).confirm(function (data) {
    	
    	console.log(data);
    	if (is_somthing) { 
    		this.transactionConfirm(data); 
    	} else {
    		this.removePaymentWindow(); 
    	}
    }).close(function (data) {
       
        console.log(data);
    }).done(function (data) {
    	
    	console.log(data);
    });
    </script>
    
    
    
    
    