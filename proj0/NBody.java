public class NBody{

	public static double readRadius(String filename){
		In in = new In(filename);

		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}

	public static Body[] readBodies(String filename){
		In in = new In(filename);
		Body[] planets = new Body[5];

		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		
		for(int i =0; i< 5; i++){
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
		
		public static void main(String[] args){

			double T = Double.parseDouble(args[0]);
			double dt = Double.parseDouble(args[1]);
			String filename = args[2];
			double radius = readRadius(filename);
			Body[] Bodys = readBodies(filename);
			
			
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
				StdDraw.setScale(-radius, radius);
				StdDraw.picture(0, 0, "images/starfield.jpg", 2*radius, 2*radius);
				
				for(int i=0;i<Bodys.length;i++){
				Bodys[i].draw();
				}

				StdDraw.show();
				StdDraw.pause(10);
			}

			StdOut.printf("%d\n", Bodys.length);
			StdOut.printf("%.2e\n", radius);
			for (int i = 0; i < Bodys.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  	Bodys[i].xxPos, Bodys[i].yyPos, Bodys[i].xxVel,
                  	Bodys[i].yyVel, Bodys[i].mass, Bodys[i].imgFileName);   
}
		}

}

