var modelEarth;
//var location;

var World = {
	loaded: false,
	rotating: false,

	init: function initFn() {
		this.createModelAtLocation();
	},

	createModelAtLocation: function createModelAtLocationFn() {

		/*
			First a location where the model should be displayed will be defined. This location will be relativ to the user.	
		*/
		var location = new AR.RelativeLocation(null, -40, 0, 1);
		var location2 = new AR.RelativeLocation(location, 0, 1, 1);

		/*
			Next the model object is loaded.
		*/
		modelEarth = new AR.Model("pin_with_texture.wt3", {
			onLoaded: this.worldLoaded,
			scale: {
				x: 1,
				y: 1,
				z: 1
			}
		});

		model2 = new AR.Model("pin_with_texture.wt3", {
			onLoaded: this.worldLoaded,
			scale: {
				x: 1,
				y: 1,
				z: 1
			}
		});

		/*
			Putting it all together the location and 3D model is added to an AR.GeoObject.
		*/
		var obj = new AR.GeoObject(location, {
            drawables: {
               cam: [modelEarth]
            }
        });

        var obj2 = new AR.GeoObject(location2, {
                    drawables: {
                       cam: [model2]
                    }
                });
	},

	worldLoaded: function worldLoadedFn() {
		World.loaded = true;
		var e = document.getElementById('loadingMessage');
		e.parentElement.removeChild(e);
	}
};

World.init();

var test = function() {
    modelEarth.scale = {
        x: 4,
        y: 4,
        z: 4
    };

    //location = new AR.RelativeLocation(null, -40, 0, -10);
}