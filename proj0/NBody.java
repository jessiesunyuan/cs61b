public class NBody{

	/** 
     * Return the radius of the universe reading from the file 
     */
	public static double readRadius(String filename){
		In in = new In(filename);

		int firstItemInFile = in.readInt();
		double Radius = in.readDouble();
		return Radius;
	}

	/** 
     * Return an array of Bodys corresponding to the bodies in the file 
     */
	public static Body[] readBodies(String filename){
		In in = new In(filename);
		int num = in.readInt();
		double Radius = in.readDouble();
		Body[] planets = new Body[num];
		
		for(int i =0; i< num; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Body(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
		}
		return planets;
	}
		

	/** 
     * Draw the initial universe state 
     */


	public static void main(String[] args){

		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] Bodys = readBodies(filename);
				
		/** 
		Draw the background
		*/
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");

		/**
		Draw the planets
		*/

		for(Body b : Bodys){
			b.draw();
		}

		/**
		Set the aniamation
		*/

		StdDraw.enableDoubleBuffering();

		double[] xForces = new double[5];
		double[] yForces = new double[5];
			
		for(double temp = 0;temp <= T;temp+=dt){
			for (int i=0;i< Bodys.length;i++ ){
				xForces[i]=Bodys[i].calcNetForceExertedByX(Bodys);
				yForces[i]=Bodys[i].calcNetForceExertedByY(Bodys);
			}
			for (int i=0;i< Bodys.length;i++ ){
				Bodys[i].update(dt,xForces[i],yForces[i]);
			}		

			/** 
			Draw the background
			*/
			StdDraw.setScale(-radius, radius);
			StdDraw.picture(0, 0, "images/starfield.jpg");

			/**
			Draw the planets
			*/

			for(Body b : Bodys){
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}

		/**
		Print the final state of the universe when time reaches T
		*/
		StdOut.printf("%d\n", Bodys.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < Bodys.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Bodys[i].xxPos, Bodys[i].yyPos, Bodys[i].xxVel,
                  	Bodys[i].yyVel, Bodys[i].mass, Bodys[i].imgFileName);   
		}	
			
	}
	
}

