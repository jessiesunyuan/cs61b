public class Body{
	public class Body {

    /** 
     * Its current x position  
     */
    public double xxPos;

    /** 
     * Its current y position
     */
    public double yyPos;

    /** 
     * Its current velocity in the x direction 
     */
    public double xxVel;

    /** 
     * Its current velocity in the y direction 
     */
    public double yyVel;

    /** 
     * Its mass  
     */
    public double mass;

    /** 
     * The name of the file that corresponds to the image that depicts the body (for example, jupiter.gif) 
     */
    public String imgFileName;

	public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }


    /** Calculate the distance between two Bodys
    */
    public double calcDistance(Body b){
    	double dx = this.xxPos-b.xxPos;
    	double dy = this.yyPos-b.yyPos;
    	return Math.sqrt(dx*dx + dy*dy);
    }

    /**Calculate the force exerted on this body by the given body
    */
    public double calcForceExertedBy(Body b){
    	double G = 6.67e-11;
    	double F = (G * this.mass * b.mass)/Math.pow(this.calcDistance(b), 2);
    	return F;
	}

	/**Calculate the force in x direction and y direction
    */
	public double calcForceExertedByX(Body b){
		double Fx = this.calcForceExertedBy(b)*(b.xxPos-this.xxPos)/this.calcDistance(b);
		return Fx;
	}

	public double calcForceExertedByY(Body b){
		double Fy = this.calcForceExertedBy(b)*(b.yyPos-this.yyPos)/this.calcDistance(b);
		return Fy;
	}

	/**Calculate the net force in x direction and y direction
    */

	public double calcNetForceExertedByX(Body[] allBodys){
		double ForceByX = 0;
		for(Body b : allBodys){
			if(!this.equals(b)){
				ForceByX += this.calcForceExertedByX(b);
			}
		}
		return ForceByX;
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		double ForceByY = 0;
		for(Body b : allBodys){
			if(!this.equals(b)){
				ForceByY += this.calcForceExertedByY(b);
			}
		}
		return ForceByY;
	
	}

	/** Update the velocity and position of the Body under the effect of force
	*/

	public void update(double dt, double fX, double fY){
		this.xxVel = this.xxVel + dt*(fX/this.mass);
		this.yyVel = this.yyVel + dt*(fY/this.mass);
		this.xxPos = this.xxPos + dt*(this.xxVel);
		this.yyPos = this.yyPos + dt*(this.yyVel);
	}

	/** Draw the picture of the body according to its position
	*/

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}
}