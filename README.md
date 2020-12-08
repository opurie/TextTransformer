# TextTransformer

[![Build Status](https://travis-ci.com/opurie/TextTransformer.svg?token=H5cCQipBdbsM9g8vuhuQ&branch=main)](https://travis-ci.com/opurie/TextTransformer)

## Main concept
**TextTransformer** is supposed to be enabling text editing in means of transforming its shape (e.g case of letters), eliminates mistakes such as duplicates by removing them or eliminating diacritical marks.
Application will be available by using **_GUI_** but also by **_REST API_** what will allow you to integrate it with other existing tools.

## Functions preview
- **Changing the case of letters:**
  - **LowerCase()** - tranforms parsed chain of letters into lower case letters
  - **UpperCase()** - tranforms parsed chain of letters into upper case letters
  - **Capitalize()** - transforms each first letter of each word into upper case letter

  _Letters of english alphabet support only (UTF-8)_

- **Changing the text order:**
  - **Inverse()** - inverses the text with keeping the upper case letters positions - "Run Forest" -> _"Nur Tserof"_

- **Finding duplicates** - checks if there is any duplicate of word in the given text and removes them - "I do do like chocolate chocolate" -> _"I do like chocolate"_

- **Expanding predefined shortcuts** - "E.g" -> _"For example"_ (keeps the case of letters)

  _Letters of english alphabet support only (UTF-8)_

- **Wrapping predefined expressions to shortcuts** - "for example" -> _"e.g"_

- **Changing numbers to text** - "Give me 567 dollars" -> _"Give me five hundred sixty seven dollars"_

  _Numbers from 1 to 1000 supported_

- **Changing text format to Latex-supported** - "John Smith & Sons" -> _"John Smith \\& Sons"_

- **Link to sprint and product backlog** - https://docs.google.com/spreadsheets/d/1oIk3Irfk_io74p7xOVZSHcBx9EH_1RhBiMiXhdUYSms/edit?fbclid=IwAR16OSp_07S-jNZBrfpfm6t8Su_cf_lAj1m_DqNe86eDljv3YS3_X0d6GNk#gid=1503080623
