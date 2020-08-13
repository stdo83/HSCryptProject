class Test {

    public static void main(String[] args) {
        new Programmer(1);
//        new TeamLead(1);
    }

    public static class TeamLead {
        private static int numTeamLead;

        public TeamLead(int numTeamLead) {
            this.numTeamLead = numTeamLead;
            employ();
        }

        protected static void employ() {
            System.out.println(numTeamLead + " teamlead");
        }

    }

    public static class Programmer extends TeamLead {
        private static int numProgrammer;

        public Programmer(int numProgrammer) {
            super(numProgrammer);
            this.numProgrammer = numProgrammer;
            employ();

        }

        protected static void employ() {
            System.out.println(numProgrammer + " programmer");
        }
    }
}