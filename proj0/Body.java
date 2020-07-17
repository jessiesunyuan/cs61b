public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	/** Body constructor
	*/
	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
    }

    /** copy a Body object
    */
    public Body(Body b){
    	this.xxPos = b.xxPos;
    	this.yyPos = b.yyPos;
    	this.xxVel = b.xxPos;
    	this.yyVel = b.yyVel;
    	this.mass = b.mass;
    	this.imgFileName = b.imgFileName;

    }

    /** 
    */
    public double calcDistance(Body b){
    	double DisX;
    	double DisY;
    	DisX = this.xxPos-b.xxPos;
    	DisY = this.yyPos-b.yyPos;
    	return Math.sqrt(DisX*DisX + DisY*DisY);
    }

    public double calcForceExertedBy(Body b){
    	double F = (6.67*(Math.pow(10,-11))*this.mass*b.mass)/(this.calcDistance(b)*this.calcDistance(b));
    	return F;
	}

	public double calcForceExertedByX(Body b){
		return this.calcForceExertedBy(b)*(b.xxPos-this.xxPos)/calcDistance(b);
	}

	public double calcForceExertedByY(Body b){
		return this.calcForceExertedBy(b)*(b.yyPos-this.yyPos)/calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double ForceByX = 0;
		for(int i=0;i<allBodys.length;i++){
			if(this.equals(allBodys[i])){
				continue;
			}
			ForceByX += this.calcForceExertedByX(allBodys[i]);
		}
		return ForceByX;
	}
	public double calcNetForceExertedByY(Body[] allBodys){
		double ForceByY = 0;
		for(int i=0;i<allBodys.length;i++){
			if(this.equals(allBodys[i])){
				continue;
			}
			ForceByY += this.calcForceExertedByY(allBodys[i]);
		}
		return ForceByY;
	
	}

	public void update(double dt, double fX, double fY){
		this.xxVel = this.xxVel + dt*(fX/this.mass);
		this.yyVel = this.yyVel + dt*(fY/this.mass);
		this.xxPos = this.xxPos + dt*(this.xxVel);
		this.yyPos = this.yyPos + dt*(this.yyVel);
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}
}