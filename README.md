# Cose utili per l'esame
### Passare parametri da riga di comando (ma con il run di VScode)
'Test' `"$(cat args-1.txt)”`
### Equals()
```java
@Override
public boolean equals(Object o) {
    // self check
    if (this == o)
        return true;
    // null check
    if (o == null)
        return false;
    // type check and cast
    if (getClass() != o.getClass())
        return false;
    Person person = (Person) o;
    // field comparison
      //== per le primitive
      //equals per i reference
}
```
### hashCode()
```java
  @Override
  public int hashCode() {
    return Objects.hash(nome, materiale);
  }
```
### Iteratore con classe innestata anonima
```java
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            int next = primoGiorno();

            @Override
            public boolean hasNext() {
                return next < primoGiorno() + repliche;
            }

            @Override
            public Integer next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                int r = next;
                next++;
                return r;
            }
        };
    }
```
<br>
