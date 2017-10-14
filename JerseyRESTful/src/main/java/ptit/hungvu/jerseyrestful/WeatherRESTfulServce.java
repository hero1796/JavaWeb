package ptit.hungvu.jerseyrestful;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/weather")
public class WeatherRESTfulServce {
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	@Path("{location}/{date}")
	@POST
	@Produces("application/json")
	public String getWeather_JSON(@PathParam("location") String location, //
			@PathParam("date") String dateStr) {

		Date date = null;
		if (dateStr == null || dateStr.length() == 0) {
			date = new Date();
		} else {
			try {
				date = df.parse(dateStr);
			} catch (ParseException e) {
				date = new Date();
			}
		}
		
		dateStr = df.format(date);

		String[] weathers = new String[] { "Hot", "Rain", "Cold" };
		int i = new Random().nextInt(3);
		String weather = weathers[i];

		return "{" //
				+ "'date': '" + dateStr + "'," //
				+ "'location': '" + location + "'," //
				+ "'info': '" + weather + "'" //
				+ "}";
	}
	@Path("{location}")
	@GET
	@Produces("application/json")
	public String getWeather_JSON(@PathParam("location") String location) {
		return getWeather_JSON(location, null);
	}
	
}

