var modelEarth;
var model2;
//var location;
const NB_PINS = 10;
var pins = [];
var relativeLocationPins = [];

var World = {
	loaded: false,
	rotating: false,

	init: function initFn(latitude, longitude, altitude, accuracy) {

	    console.log(latitude);
		this.createModelAtLocation(latitude, longitude, altitude);
	},

	createModelAtLocation: function createModelAtLocationFn(latitude, longitude, altitude) {

		/*
			First a location where the model should be displayed will be defined. This location will be relativ to the user.
		*/
		var location = new AR.RelativeLocation(null, -40, 0, 1);
		var location2 = new AR.GeoLocation(43.6066773, 1.4496225);
		//43.6066773, 1.4496225

        for (var i = 0; i < NB_PINS; i++) {
            var loc = new AR.RelativeLocation(location, pinPositions[i].north * -4, pinPositions[i].east * 3, -5);
            relativeLocationPins.push(loc);
        }

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

        /*var obj = new AR.GeoObject(location2, {
                                           drawables: {
                                              cam: [modelEarth]
                                           }
                                        });*/
        for (var i = 0; i < NB_PINS; i++) {
             var obj = new AR.GeoObject(relativeLocationPins[i], {
                                   drawables: {
                                      cam: [modelEarth]
                                   }
                                });
             pins.push(obj);
        }



		/*
			Putting it all together the location and 3D model is added to an AR.GeoObject.
		*/
		/*var obj = new AR.GeoObject(location, {
            drawables: {
               cam: [modelEarth]
            }
        });

        var obj2 = new AR.GeoObject(location2, {
                    drawables: {
                       cam: [modelEarth]
                    }
                });

        var obj3 = new AR.GeoObject(location3, {
                            drawables: {
                               cam: [modelEarth]
                            }
                        });*/
	},

	worldLoaded: function worldLoadedFn() {
		World.loaded = true;
		var e = document.getElementById('loadingMessage');
		e.parentElement.removeChild(e);
	}
};

//World.init(40,40,40);

AR.context.onLocationChanged = function(latitude, longitude, altitude, accuracy){
    World.init(latitude, longitude, altitude);
};

function test(cypher) {
console.log("LALALA");
    /*modelEarth.scale = {
        x: 4,
        y: 4,
        z: 4
    };*/

    //location = new AR.RelativeLocation(null, -40, 0, -10);
};

//Création des 10 quilles à afficher
//Selon la localisation
/*function createPins(latitude, longitude, altitude) {
    console.log(latitude + ", " + longitude + ", " + altitude);

    //Création de 10 quilles
    // La première quille se dessine sur le post-it
        /*for (var i = 0; i < NB_PINS; i++) {
            //10 localisations et 10 modèles
            var modelLoc = {};
        }

    World.init(latitude, longitude, altitude);
};*/

var pinPositions = [
            {north: 0,east: 0},
            {north: 1,east: 1},
            {north: 1, east: -1},
            {north: 2,east: 2},
            {north: 2, east: 0},
            {north: 2,east: -2},
            {north: 3,east: 3},
            {north: 3,east: 1},
            {north: 3,east: -1},
            {north: 3,east: -3}
];