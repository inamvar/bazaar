
function showMap(address){
    try {
        $('#gmap').gmap3({
            marker: {
                address: address
            },
            map: {
                options: {
                    zoom: 15
                }
            }
        });
    }catch(e) {};
};


function startCountdown(){

	$('.countdown').each(function(){
		var count = $(this);
		$(this).countdown({
			zeroCallback: function(options) {
			$('btn-buy').hide();
			$('btn-soldout').removeClass('hidden');
			$('btn-soldout').show();	
				/*$(count).countdown({
					unixFormat : true
				});*/
			}
		});
		
		
	});

};