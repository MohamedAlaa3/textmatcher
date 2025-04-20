# Text Matcher

A Spring Boot application that compares a base text file against multiple `.txt` files in a folder to calculate similarity scores using simple word overlap. The results are returned via a REST API, sorted by filename. Supports multithreading for faster performance.

---

## 🔧 Features

- 📝 Compares a base file with multiple text files
- ⚡ Fast processing using multithreading
- 🧠 Simple word-based matching
- 🌐 REST API to fetch results
- 📂 Automatically ignores system files (e.g., `.DS_Store`)

---

## 📦 API Endpoint

### `GET /api/match-results`

Returns a JSON list of file names with their similarity score:

```json
[
  {
    "fileName": "file1.txt",
    "score": 100
  },
  {
    "fileName": "file2.txt",
    "score": 50
  },
  {
    "fileName": "file6.txt",
    "score": 50
  },
  {
    "fileName": "file5.txt",
    "score": 87.5
  },
  {
    "fileName": "file3.txt",
    "score": 0
  },
  {
    "fileName": "file4.txt",
    "score": 75
  }
]

<img width="1440" alt="image" src="https://github.com/user-attachments/assets/9b388b14-ad47-49b2-89bd-c38e1274a5ad" />

