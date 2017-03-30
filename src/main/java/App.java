import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("events", Event.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/signup", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/signup.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/gamers/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Gamer gamer = new Gamer(name);
      gamer.save();
      String url = String.format("/gamers/" + gamer.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/gamers/:gamer_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Gamer gamer = Gamer.find(Integer.parseInt(request.params(":gamer_id")));
      model.put("gamer", gamer);
      model.put("events", Event.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("gamers/:gamer_id/new-event", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Gamer gamer = Gamer.find(Integer.parseInt(request.params(":gamer_id")));
      model.put("gamer", gamer);
      model.put("platforms", Platform.all());
      model.put("template", "templates/event-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("gamers/:gamer_id/events/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String eventName = request.queryParams("event_name");
      String gameName = request.queryParams("game_name");
      int platformId = Integer.parseInt(request.queryParams("platform_id"));
      int maxPlayers = Integer.parseInt(request.queryParams("max_players"));
      String date = request.queryParams("date");
      String time = request.queryParams("time");

      Gamer gamer = Gamer.find(Integer.parseInt(request.params(":gamer_id")));

      Event event = new Event(eventName, gameName, platformId, maxPlayers, date, time, gamer.getId());
      event.save();

      String url = String.format("/gamers/" + gamer.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
