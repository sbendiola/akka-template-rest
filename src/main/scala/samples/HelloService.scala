package samples

import se.scalablesolutions.akka.actor.{Actor, ActorRef, ActorRegistry}
import ActorRegistry.actorFor
import Actor.actorOf

import javax.ws.rs.{GET, Path, Produces}

@Path("/hello")
class HelloService(actor: ActorRef) {
  def this() {
    this(actorFor[HelloActor].get)
  }
  
	@GET
	@Produces(Array("text/html"))
	def hello:String = {
    (actor !! Hello).map(_.toString).getOrElse("error in hello")
  }

}

case object Hello

class HelloActor extends Actor {

	def receive = {
		case Hello => self.reply(<h1>Hello, World</h1>.toString)
	}
  
}