<h1 align="center">Braille Translator</h1>

### Description
A C++ text-to-Braille translator that validates sentence input, renders six-dot cells in a horizontal layout, and reports character frequencies across a session. Braille mappings for letters, punctuation, and spaces are loaded from `data/braille-mappings.txt`.

### Repository layout

```text
data/    Braille character mappings
extras/  Output screenshot
src/     Translator source code
```

### Build and run

```bash
cmake -S . -B build
cmake --build build
./build/braille-translator
```

Run the executable from the repository root so it can resolve the mapping file.

### Working screenshot
  <div align="center">
    <img src = "https://github.com/SameetAsadullah/braille-translator/blob/master/extras/working-ss-1.png" alt = "" width="800px"/>
  </div>
