package com.lmig;

import static spark.Spark.get;
import static spark.Spark.port;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;


import java.util.ArrayList;
import com.google.gson.Gson;


public class AlbumCatalog {

	public static void main(String[] args) {



		ArrayList<Album> albums = new ArrayList<Album>();

		albums.add(new Album("Chicken Fried", 0, "Zac Brown", "Contemporary Country"));
		albums.add(new Album("Title2", 1, "Jeb2", "Rock2"));
		albums.add(new Album("Title3", 2, "Jeb3", "Rock3"));
		albums.add(new Album("Title4", 3, "Jeb4", "Rock4"));

		port(3000);

		// query to get values
		get("/Album", (request, response) -> {
			// String albumTitle = request.queryParams("albumTitle");
			int id = Integer.parseInt(request.queryParams("id"));
			albums.get(id);
			return "Album Title " + albums.get(id).toString();
		});

		get("/create", (request, respone) -> {
			
			String albumTitle = request.queryParams("albumTitle");
			//int id = Integer.parseInt(request.queryParams("id"));
			String artistName = request.queryParams("artistName");
			String genre = request.queryParams("genre");
			albums.add(new Album(albumTitle, 4, artistName, genre));
			return albums.get(4).toString();

		});
				
		
		get("/", (request, response)-> {
            // convert list of albums into html
			
			//String h1 = "<h1>Albums</h1>";		
            //String body = "";
            //for (int i = 0; i < albums.size(); i++){
            	//body = body + "<div> Album Name " + albums.get(i).albumTitle + " Album Id " + albums.get(i).id + " Artist Name " + albums.get(i).artistName + " Genre " + albums.get(i).genre + "</div>";
            	
            	  JtwigTemplate template = JtwigTemplate.classpathTemplate("example.jtwig");
                  JtwigModel model = JtwigModel.newModel().with("albums", albums);

                  return template.render(model);
              });
            	
		
	      get("/albumsjson", (requst, response) -> {
	          

	            Gson gson = new Gson();
	   
	            return gson.toJson(albums);
	        });
           
	     get("/data", (request, response)-> {
	   	  JtwigTemplate template = JtwigTemplate.classpathTemplate("example.jtwig");
          JtwigModel model = JtwigModel.newModel();

          return template.render(model);
      });    
            
//            String html = "<!DOCTYPE html><html><head>" + h1 + "</head><body>" + body + "</body></html>";
//            return html;
//        });

	}

}

class Album {
	public String albumTitle;
	public int id;
	public String artistName;
	public String genre;

	@Override
	public String toString() {
		return "Album [albumTitle=" + albumTitle + ", id=" + id + ", artistName=" + artistName + ", genre=" + genre
				+ "]";
	}

	// constructor
	public Album(String albumTitle, int id, String artistName, String genre) {
		this.albumTitle = albumTitle;
		this.id = id;
		this.artistName = artistName;
		this.genre = genre;
	}

}