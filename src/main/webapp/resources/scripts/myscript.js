
function showMap(address){
	var loc = address.split(',');
    try {
       $('#gmap').gmap3({
            marker: {
            	 latLng:[loc[0], loc[1]]
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