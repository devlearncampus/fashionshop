const MoneyConverter=new Intl.NumberFormat("ko-KR");

function format(price){
	return MoneyConverter.format(price);
}