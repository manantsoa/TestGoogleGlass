var modelEarth;
var model2;
//var location;
const NB_PINS = 10;

var World = {
	loaded: false,
	rotating: false,

	init: function initFn(latitude, longitude, altitude) {
		this.createModelAtLocation(latitude, longitude, altitude);
	},

	createModelAtLocation: function createModelAtLocationFn(latitude, longitude, altitude) {

		/*
			First a location where the model should be displayed will be defined. This location will be relativ to the user.	
		*/

		var location = new AR.RelativeLocation(null, -40, 0, 1);
		var location3 = new AR.GeoLocation(latitude, longitude, altitude + 1);
		var location2 = new AR.RelativeLocation(location, 0, 5, 1);

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
                       cam: [modelEarth]
                    }
                });

        var obj3 = new AR.GeoObject(location3, {
                            drawables: {
                               cam: [modelEarth]
                            }
                        });
	},

	worldLoaded: function worldLoadedFn() {
		World.loaded = true;
		var e = document.getElementById('loadingMessage');
		e.parentElement.removeChild(e);
	}
};

//World.init();

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
function createPins(latitude, longitude, altitude) {
    console.log(latitude + ", " + longitude + ", " + altitude);
    var pins = [];

    //Création de 10 quilles
    // La première quille se dessine sur le post-it
        /*for (var i = 0; i < NB_PINS; i++) {
            //10 localisations et 10 modèles
            var modelLoc = {};
        }*/

    World.init(latitude, longitude, altitude);
};