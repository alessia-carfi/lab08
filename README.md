# CONSEGNA LAB08

### LAB 81-bad-gui-io

### CASA 82-simple-mvc-io

- Nella classe **Controller.java** ho inizializzato soltanto una variabile privata File che avrebbe rappresentato il mio **currentFile**.
- Nel mettono per salvare il file ho fatto in **try** che tramite un **PrintStream** salvasse le stringhe date in input nel file e poi ho fatto un **catch** che printasse su console gli eventuali errori
- Nel metodo che restituiva il file e quello che restituiva la path ho aggiunto un controllo per verificare che il file non fosse nullo ma non ho fatto il controllo nel metodo per settare la nuova destinazione.
- Ho aggiunto anche un metodo statico che mi avrebbe permesso di visualizzare il contenuto del nuovo file da selezionare nella **SimpleGUIWithFileChooser** anche nel **JTextField**.

- Nella classe SimpleGUI.java ho inizializzato una variabile statica per il titolo da passare al **JFrame** e anche una variabile statica per la **path** del _file.txt_ da creare che avrebbe permesso poi a questo di prendere il nome della classe stessa.
- Nel costruttore, per l'**ActionListener** del bottone, ho fatto un **if**, se il risultato del **JOptionPane.showConfirmDialog()** fosse stato **== 0** (quindi se l'utente avesse dato una risposta positiva) avrebbe fatto cominciare **try** per scrivere il contenuto del **JTextField** sul file.

- Nella classe **SimpleGUIWithFileChooser** ho usato un approccio simile alla classe SimpleGUI. Per il nuovo tasto da creare ho fatto un **try** dove se il **JFileChooser** riportava risposta affermativa, allora il **Controller** _settava_ il _nuovo file_ come _current file_ e il **JTextField** riportava il testo del file se lo aveva.

### CASA 83-mvc-io

- Nella classe **SimpleController**, nel metodo per settare la _nextString_, ho usato un **if** per controllare se la stringa fosse **null** (non ho pensato al Object.requireNonNull nonostante l'abbia già utilizzato più volte).
- Nel metodo per ritornare la _History_ ho fatto un **return** della copia dell'**ArrayList** che avevo dichiarato fuori.

- Nella classe **SimpleGUI**, nel costruttore ho creato un _pannello principale_, aggiunto un **JTextField** a nord e una **JTextArea** al centro e poi creato _un altro pannello_ dove ho posizionato i _due bottoni_, uno a est e l'altro a ovest e poi questo pannello l'ho aggiunto al pannello principale a sud.
- Nell'**ActionListener** del tasto _Print_ ho fatto la stessa cosa ma dentro un **try catch** che mi avrebbe fatto vedere un **JOptionPane** in caso di errore
- Nell'**ActionListener** del tasto _History_ ho fatto la stessa cosa ma anche qui dentro un **catch** che mi avrebbe fatto vedere un **JOptionPane** in caso di errore e non ho fatto il controllo nel caso in cui la _lista history_ fosse stata vuota perché in quel caso semplicemente continua a fare vedere il vuoto nella **JTextArea**.

### CASA 84-advanced-mvc

- Nella classe **DrawNumberApp** per leggere i valori nel _file.yml_ ho creato un altro file con un'altra classe _ConfugurationReader_ e ho usato un **BufferReader** e un **FileReader** per leggere il file, ho usato **spit()** per le stringhe perché una _regex_ mi sembrava eccessiva per fare un lavoro così semplice e poi ho salvato i valori in un **Hashmap** che ho usato per creare le variabili _minimum_, _maximum_ e _attempts_ che ho poi usato nella classe **DrawNumberApp**.
  > col senno di poi avrei scritto meglio la classe ConfigurationReader.
