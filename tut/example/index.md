---
layout:  page
title: "Example: analyzing a corpus of texts"
---


##  A useful group of libraries

Implementations of the `MidOrthography` trait simplify several tasks that commonly arise in working with a citable corpus of texts.  Import the `cite` and `ohco2` libraries along with the MID validator:


```tut:silent
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
```

## Tokenize a corpus

```tut:invisible
val cex = """urn:cts:omar:stoa0179.stoa001.omar:1.4.4#sacerdos vincta in custodiam datur; pueros in profluentem aquam mitti iubet. forte quadam divinitus super ripas Tiberis effusus lenibus stagnis nec adiri usquam ad iusti cursum poterat amnis et posse quamvis languida mergi aqua infantes spem ferentibus dabat.
urn:cts:omar:stoa0179.stoa001.omar:1.4.5#ita, velut defuncti regis imperio, in proxima eluvie, ubi nunc ficus Ruminalis est — Romularem vocatam ferunt —, pueros exponunt. vastae tum in his locis solitudines erant.
urn:cts:omar:stoa0179.stoa001.omar:1.4.6#tenet fama, cum fluitantem alveum, quo expositi erant pueri, tenuis in sicco aqua destituisset, lupam sitientem ex montibus, qui circa sunt, ad puerilem vagitum cursum flexisse; eam summissas infantibus adeo mitem praebuisse mammas, ut lingua lambentem pueros magister regii pecoris invenerit—
urn:cts:omar:stoa0179.stoa001.omar:1.4.7#Faustulo fuisse nomen ferunt —; ab eo ad stabula Larentiae uxori educandos datos. sunt, qui Larentiam vulgato corpore lupam inter pastores vocatam putent; inde locum fabulae ac miraculo datum.
urn:cts:omar:stoa0179.stoa001.omar:1.4.8#ita geniti itaque educati, cum primum adolevit aetas, nec in stabulis nec ad pecora segnes, venando peragrare saltus.
urn:cts:omar:stoa0179.stoa001.omar:1.4.9#hinc robore corporibus animisque sumpto iam non feras tantum subsistere, sed in latrones praeda onustos impetus facere pastoribusque rapta dividere et cum his crescente in dies grege iuvenum seria ac iocos celebrare.
"""
val corpus = Corpus(cex)
```



Acquire a corpus of citable texts.  For this example, we'll use a very short selection from Livy named `corpus` (created behind the scenes of this page) containing six citable nodes. (If you're curious, [here's the corpus](source).)

```tut
corpus.size
```




An `MidOrthography` understands the semantics of a text's orthography so that it can be analyzed in terms of a specified set of token classes.  For this text, we'll import the `Latin23Alphabet` orthography and tokenize our corpus.


```tut:silent
import edu.holycross.shot.latin.Latin23Alphabet
val tokens = Latin23Alphabet.tokenizeCorpus(corpus)
```


## Sumarize a Vector of tokens

The `MidOrthography` object can work with the resulting Vector of tokens.


Get an overview of the distribution of tokens by category:

```tut
MidOrthography.categoryHistogram(tokens)
```


Get a complete concordance of alphabetically sorted tokens mapped to CtsUrns where they occur.  Passages for each token are sorted by document order.

```tut
val concordance = MidOrthography.concordance(tokens)
// show passages of URNs where "ferunt" appears
concordance("ferunt").map(_.passageComponent)
```


We might want to analyze only the tokens of a particular type:

```tut
val lexical = tokens.filter(_.tokenCategory.toString == "Some(LexicalToken)")
```

Lists of unique vocabulary are alphabetically sorted:

```tut
val vocab = MidOrthography.vocabulary(lexical)
```

Histograms of tokens are sorted by frequency with secondary alpahbetic sort on the token string.

```tut
val tokenHisto = MidOrthography.tokenHistogram(lexical)
```
